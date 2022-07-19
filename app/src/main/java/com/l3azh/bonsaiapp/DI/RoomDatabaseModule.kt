package com.l3azh.bonsaiapp.DI

import android.content.Context
import androidx.room.Room
import com.l3azh.bonsaiapp.Db.BonsaiDb
import com.l3azh.bonsaiapp.Db.Dao.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): BonsaiDb {
        return Room.databaseBuilder(
            appContext,
            BonsaiDb::class.java,
            "BonsaiDB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCartDao(bonsaiDb: BonsaiDb):CartDao = bonsaiDb.cartDao()
}