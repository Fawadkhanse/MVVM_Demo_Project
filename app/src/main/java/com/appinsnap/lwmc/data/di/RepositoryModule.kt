package health.dept.ecarecameroon.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import health.dept.ecarecameroon.core.repository.doctorrepository.LwmsRepository
import health.dept.ecarecameroon.core.repository.doctorrepository.LwmsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun lwmsRepository(lwmsRepositoryImpl: LwmsRepositoryImpl) : LwmsRepository
}