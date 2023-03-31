package health.dept.ecarecameroon.core.repository.doctorrepository

import com.appinsnap.lwmc.dataclasses.responsehandler.Either
import com.appinsnap.lwmc.dataclasses.model.request.LoginDataModel
import com.appinsnap.lwmc.dataclasses.model.response.LoginResponseModel
import javax.inject.Singleton

@Singleton
interface LwmsRepository {
    suspend fun loginApi(
        requestBody: LoginDataModel
    ): Either<LoginResponseModel>

    suspend fun addShopApi(
        requestBody: LoginDataModel
    ): Either<LoginResponseModel>
}