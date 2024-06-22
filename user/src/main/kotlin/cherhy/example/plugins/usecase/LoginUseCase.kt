package cherhy.example.plugins.usecase

import cherhy.example.plugins.service.ReadUserService
import com.cherhy.common.annotation.Facade

@Facade
class LoginUseCase(
    private val readUserService: ReadUserService,
) {
    fun execute() {
        TODO()
    }
}