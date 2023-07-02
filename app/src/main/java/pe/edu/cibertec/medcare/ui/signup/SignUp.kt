package pe.edu.cibertec.medcare.ui.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.medcare.R
import pe.edu.cibertec.medcare.data.repository.UserRepository
import pe.edu.cibertec.medcare.ui.Route
import pe.edu.cibertec.medcare.ui.theme.MedCareTheme
import pe.edu.cibertec.medcare.util.Result


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController) {

    val username = remember {
        mutableStateOf(TextFieldValue())
    }
    val password = remember {
        mutableStateOf(TextFieldValue())
    }

    val fname = remember {
        mutableStateOf(TextFieldValue())
    }

    val lname = remember {
        mutableStateOf(TextFieldValue())
    }

    val email = remember {
        mutableStateOf(TextFieldValue())
    }

    val confirmPassword = remember {
        mutableStateOf(TextFieldValue())
    }

    val showPassword = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val userRepository = UserRepository()

    val errorCreate = "Debe ingresar todos los campos"

    val image = painterResource(R.drawable.new_registration_icon)
    val imageModifier = Modifier.size(80.dp)

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
        Text(text = "Register")
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
            label = { Text(text = "First Name") },
            value = fname.value,
            onValueChange = {
                fname.value = it
            },
            leadingIcon = { Icon(Icons.Default.Badge, null) },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Last Name") },
            value = lname.value,
            onValueChange = {
                lname.value = it
            },
            leadingIcon = { Icon(Icons.Default.Badge, null) },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Email") },
            value = email.value,
            onValueChange = {
                email.value = it
            },
            leadingIcon = { Icon(Icons.Default.Email, null) },
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
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Confirm password") },
            value = confirmPassword.value,
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            onValueChange = {
                confirmPassword.value = it
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
                if (
                    username.value.text != "" &&
                    password.value.text != "" &&
                    fname.value.text != "" &&
                    lname.value.text != "" &&
                    email.value.text != "" &&
                    confirmPassword.value.text != ""
                ) {
                    userRepository.createUser(
                        username.value.text,
                        password.value.text,
                        fname.value.text,
                        lname.value.text,
                        email.value.text,
                        confirmPassword.value.text
                    ) { result ->
                        if (result is Result.Success) {
                            navController.navigate(Route.Drug.route)
                        } else {
                            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    Toast.makeText(context, errorCreate, Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
            Text(text = "Sign up")
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
                navController.navigate(Route.Login.route)

            }) {
            Text(text = "Sign in")
        }
        TextButton(
            onClick = { /*TODO*/ }) {
            Text(text = "Forgot password")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    MedCareTheme {
        SignUp(navController = rememberNavController())
    }
}
