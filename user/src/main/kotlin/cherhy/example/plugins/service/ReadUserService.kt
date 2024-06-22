package cherhy.example.plugins.service

import cherhy.example.plugins.repository.UserRepository
import com.cherhy.common.annotation.ReadService

@ReadService
class ReadUserService(
    private val userRepository: UserRepository,
) {

}