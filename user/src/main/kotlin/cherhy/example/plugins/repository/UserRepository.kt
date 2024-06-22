package cherhy.example.plugins.repository

interface UserRepository {
    fun save()
    fun update()
    fun delete()
    fun findById()
    fun findAll()
    fun findByEmail()
}

class UserRepositoryImpl: UserRepository {
    override fun save() {
        TODO("Not yet implemented")
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }

    override fun findById() {
        TODO("Not yet implemented")
    }

    override fun findAll() {
        TODO("Not yet implemented")
    }

    override fun findByEmail() {
        TODO("Not yet implemented")
    }
}