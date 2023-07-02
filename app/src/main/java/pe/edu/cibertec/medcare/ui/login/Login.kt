package pe.edu.cibertec.medcare.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.medcare.data.repository.UserRepository
import pe.edu.cibertec.medcare.ui.Route
import pe.edu.cibertec.medcare.ui.theme.MedCareTheme
import pe.edu.cibertec.medcare.util.Result
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pe.edu.cibertec.medcare.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {

    val username = remember {
        mutableStateOf(TextFieldValue())
    }

    val password = remember {
        mutableStateOf(TextFieldValue())
    }
    val showPassword = remember {
        mutableStateOf(false)
    }

    val userRepository = UserRepository()

    val context = LocalContext.current

    val image = painterResource(R.drawable.logo_modificadopositivo_movil_vert)
    val imageModifier = Modifier.size(300.dp)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = imageModifier
        )
        Text(
            text = "MedCare",
            style = TextStyle(
                fontSize = 40.sp,
                color = Color(57, 192, 224),
                fontWeight = FontWeight.Bold
            )
        )
        Text(text = "Login", modifier = Modifier.padding(20.dp))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = {
                username.value = it
            },
            leadingIcon = { Icon(Icons.Default.Person, null) },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            onValueChange = {
                password.value = it
            },
            leadingIcon = { Icon(Icons.Default.Lock, null) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        showPassword.value = !showPassword.value
                    }) {
                    if (showPassword.value) {
                        Icon(Icons.Default.Visibility, null)
                    } else {
                        Icon(Icons.Default.VisibilityOff, null)
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),

            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(237, 148, 143),
                contentColor = Color.White
            ),
            onClick = {
                userRepository.login(username.value.text, password.value.text) { result ->
                    if (result is Result.Success<*>) {
                        navController.navigate(Route.Drug.route)
                    } else {
                        Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }) {
            Text(text = "Sign in")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(237, 148, 143),
                contentColor = Color.White
            ),
            onClick = {
                navController.navigate(Route.SignUp.route)

            }) {
            Text(text = "Sign up")
        }
        TextButton(
            onClick = { /*TODO*/ }) {
            Text(text = "Forgot password")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MedCareTheme {
        Login(navController = rememberNavController())
    }
}

