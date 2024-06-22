package cherhy.example.plugins.usecase

import cherhy.example.plugins.service.ReadUserService
import cherhy.example.plugins.service.WriteUserService
import com.cherhy.common.annotation.Facade

@Facade
class SignUpUseCase(
    private val readUserService: ReadUserService,
    private val writeUserService: WriteUserService,
) {
    fun execute() {
        TODO()
    }
}