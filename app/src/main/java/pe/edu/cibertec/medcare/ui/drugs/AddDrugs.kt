package pe.edu.cibertec.medcare.ui.drugs

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
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.twotone.Pin
import androidx.compose.material.icons.twotone.Pix
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.medcare.R
import pe.edu.cibertec.medcare.data.repository.DrugRepository
import pe.edu.cibertec.medcare.ui.Route
import pe.edu.cibertec.medcare.ui.theme.MedCareTheme
import pe.edu.cibertec.medcare.util.Result


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDrugs(navController: NavController) {

    val name = remember {
        mutableStateOf(TextFieldValue())
    }
    val concent = remember {
        mutableStateOf(TextFieldValue())
    }

    val type = remember {
        mutableStateOf(TextFieldValue())
    }

    val presentation = remember {
        mutableStateOf(TextFieldValue())
    }

    val image = remember {
        mutableStateOf(TextFieldValue())
    }

    val imageIcon = painterResource(R.drawable.drugs)
    val imageModifier = Modifier.size(200.dp)

    val context = LocalContext.current

    val drugRepository = DrugRepository()

    val errorCreate = "Debe ingresar todos los campos"

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = imageIcon,
            contentDescription = null,
            modifier = imageModifier
        )
        Text(text = "Create Drug")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Name") },
            value = name.value,
            onValueChange = {
                name.value = it
            },
            leadingIcon = { Icon(Icons.Default.MonitorHeart, null) },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Concent") },
            value = concent.value,
            onValueChange = {
                concent.value = it
            },
            leadingIcon = { Icon(Icons.TwoTone.Pix, null) },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Type") },
            value = type.value,
            onValueChange = {
                type.value = it
            },
            leadingIcon = { Icon(Icons.TwoTone.Pin, null) },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Presentation") },
            value = presentation.value,
            onValueChange = {
                presentation.value = it
            },
            leadingIcon = { Icon(Icons.Rounded.Book, null) },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            label = { Text(text = "Image") },
            value = image.value,
            onValueChange = {
                image.value = it
            },
            leadingIcon = { Icon(Icons.Default.Photo, null) },
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
                    name.value.text != "" &&
                    concent.value.text != "" &&
                    type.value.text != "" &&
                    presentation.value.text != "" &&
                    image.value.text != ""
                ) {
                    drugRepository.createDrugs(
                        name.value.text,
                        concent.value.text,
                        type.value.text,
                        presentation.value.text,
                        image.value.text
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
            Text(text = "Create")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddDrugsPreview() {
    MedCareTheme {
        AddDrugs(navController = rememberNavController())
    }
}
