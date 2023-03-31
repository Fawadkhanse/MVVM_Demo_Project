package health.dept.ecarecameroon.core.repository.doctorrepository

import android.content.Context
import android.util.Log
import com.appinsnap.lwmc.dataclasses.responsehandler.Either
import com.appinsnap.lwmc.R
import com.appinsnap.lwmc.data.api.Api
import com.appinsnap.lwmc.dataclasses.model.request.LoginDataModel
import com.appinsnap.lwmc.dataclasses.model.response.LoginResponseModel
import javax.inject.Inject

class LwmsRepositoryImpl @Inject constructor(
    private val api: Api,
    private val context: Context,
) : LwmsRepository {

    override suspend fun loginApi(requestBody: LoginDataModel): Either<LoginResponseModel> {
        return try {
            val response = api.callLoginApi(requestBody)

            Log.d("LWMSRepositoryImpl", "callSignUpApi: $response")

            if (response.code() ==200) {
                response.body()?.let {
                    Either.success(it)
                } ?: kotlin.run {
                    Either.error("Some thing went wrong", response.code())
                }
            } else if (response.code() == 403) {
                Either.error("Invalid username or password", response.code())
            } else {
                Either.error("Some thing went wrong", response.code())
            }
        } catch (e: Exception) {
            Either.error(context.getString(R.string.no_internet), 0)
        }
    }

    override suspend fun addShopApi(requestBody: LoginDataModel): Either<LoginResponseModel> {
        return try {
            val response = api.callLoginApi(requestBody)
            if (response.isSuccessful) {
                response.body()?.let {
                    Either.success(it)
                } ?: kotlin.run {
                    Either.error("Some thing went wrong", response.code())
                }
            } else if (response.code() == 403) {
                Either.error("Invalid username or password", response.code())
            } else {
                Either.error("Some thing went wrong", response.code())
            }
        } catch (e: Exception) {
            Either.error(context.getString(R.string.no_internet), 0)
        }
    }

}