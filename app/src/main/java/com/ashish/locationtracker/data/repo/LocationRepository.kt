package com.ashish.locationtracker.data.repo

import android.content.Context
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.ashish.locationtracker.data.db.MyLocationDatabase
import com.ashish.locationtracker.data.db.MyLocationEntity
import com.ashish.locationtracker.data.manager.MyLocationManager
import java.util.UUID
import java.util.concurrent.ExecutorService

private const val TAG = "LocationRepository"

class LocationRepository private constructor(
    private val myLocationDatabase: MyLocationDatabase,
    private val myLocationManager: MyLocationManager,
    private val executor: ExecutorService
) {

    private val locationDao = myLocationDatabase.locationDao()


    fun getLocations(): LiveData<List<MyLocationEntity>> = locationDao.getLocations()

    fun getLocation(id: UUID): LiveData<MyLocationEntity> = locationDao.getLocation(id)


    fun updateLocation(myLocationEntity: MyLocationEntity) {
        executor.execute {
            locationDao.updateLocation(myLocationEntity)
        }
    }

    fun addLocation(myLocationEntity: MyLocationEntity) {
        executor.execute {
            locationDao.addLocation(myLocationEntity)
        }
    }

    fun addLocations(myLocationEntities: List<MyLocationEntity>) {
        executor.execute {
            locationDao.addLocations(myLocationEntities)
        }
    }

    val receivingLocationUpdates: LiveData<Boolean> = myLocationManager.receivingLocationUpdates

    @MainThread
    fun startLocationUpdates() = myLocationManager.startLocationUpdates()

    @MainThread
    fun stopLocationUpdates() = myLocationManager.stopLocationUpdates()

    companion object {
        @Volatile private var INSTANCE: LocationRepository? = null

        fun getInstance(context: Context, executor: ExecutorService): LocationRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationRepository(
                    MyLocationDatabase.getInstance(context),
                    MyLocationManager.getInstance(context),
                    executor)
                    .also { INSTANCE = it }
            }
        }
    }
}