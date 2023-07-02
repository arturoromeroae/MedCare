package pe.edu.cibertec.medcare.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import pe.edu.cibertec.medcare.ui.drugs.AddDrugs
import pe.edu.cibertec.medcare.ui.login.Login
import pe.edu.cibertec.medcare.ui.signup.SignUp
import pe.edu.cibertec.medcare.ui.drugs.DrugList

@Composable
fun Home() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Login.route
    ) {

        composable(Route.SignUp.route) {
            SignUp(navController)
        }

        composable(Route.Drug.route) {
            DrugList(navController)
        }

        composable(Route.Login.route) {
            Login(navController)
        }

        composable(Route.AddDrugs.route) {
            AddDrugs(navController)
        }

    }
}

sealed class Route(val route: String) {
    object Login : Route("login")
    object Drug : Route("drug")
    object SignUp : Route("sign_up")
    object AddDrugs : Route("add_drug")
}