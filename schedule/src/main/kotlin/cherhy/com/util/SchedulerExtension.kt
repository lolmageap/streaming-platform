package cherhy.com.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun <T> schedule(
    block: suspend () -> T,
) =
    CoroutineScope(Dispatchers.Default).launch {
        while (isActive) {
            block.invoke()
        }
    }