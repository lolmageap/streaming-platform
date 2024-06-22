package cherhy.example.plugins.service

import cherhy.example.plugins.repository.UserRepository
import com.cherhy.common.annotation.WriteService

@WriteService
class WriteUserService(
    private val userRepository: UserRepository,
) {

}