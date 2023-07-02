package pe.edu.cibertec.medcare.ui.drugs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import pe.edu.cibertec.medcare.data.model.Drug
import pe.edu.cibertec.medcare.data.repository.DrugRepository
import pe.edu.cibertec.medcare.ui.Route
import pe.edu.cibertec.medcare.ui.theme.MedCareTheme
import pe.edu.cibertec.medcare.util.Result

@Composable
fun DrugList(navController: NavController) {

    val drugs = remember {
        mutableStateOf(listOf<Drug>())
    }

    val drugRepository = DrugRepository()
    val context = LocalContext.current
    var showLogoutButton = remember {
        mutableStateOf(false)
    }
    val selectedProduct = remember {
        mutableStateOf<Drug?>(null)
    }
    val openModal = remember {
        mutableStateOf(false)
    }

    drugRepository.getDrugs { result ->
        if (result is Result.Success ){
            drugs.value = result.data!!
        } else {
            Toast.makeText(context, result.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    Box {
        Column(Modifier.fillMaxWidth()) {
            // Header
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                // Icon on the right
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    onClick = {
                        showLogoutButton.value = !showLogoutButton.value
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = Color.Black
                    )

                    // Menu item button for logout
                    Box(
                        modifier = Modifier
                            .padding(top = 55.dp)
                            .align(Alignment.CenterEnd)
                    ) {
                        DropdownMenu(
                            expanded = showLogoutButton.value,
                            onDismissRequest = { showLogoutButton.value = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Logout") },
                                onClick = {
                                    navController.navigate(Route.Login.route)
                                }
                            )
                        }
                    }
                }
                // Menu on the left
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            // Scrollable content
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(drugs.value) { drug ->
                    Box(
                        modifier = Modifier
                            .padding(1.dp)
                            .background(Color(246, 246, 246))
                            .fillMaxWidth()
                            .clickable {
                                openModal.value = true
                                selectedProduct.value = drug
                            }
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(10.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Transparent,
                                        shape = CircleShape
                                    )
                            ) {
                                AsyncImage(
                                    model = drug.image,
                                    contentDescription = null
                                )
                            }
                            Column {
                                Text(
                                    text = drug.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 5.em
                                )
                                Text(text = drug.concent)
                                Text(text = drug.type)
                            }
                        }
                    }
                    Divider()
                }
            }

            if (openModal.value) {
                Dialog(
                    onDismissRequest = { openModal.value = false },
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(450.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            IconButton(
                                modifier = Modifier.align(Alignment.End),
                                onClick = { openModal.value = false }
                            ) {
                                Icon(Icons.Default.Cancel, null)
                            }
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()) {
                                AsyncImage(
                                    model  = selectedProduct.value!!.image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .align(Alignment.TopCenter)
                                )
                                Text(
                                    text = "Medicamento: " + selectedProduct.value!!.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(top = 50.dp)
                                )
                                Text(
                                    text = "Concentrado: " + selectedProduct.value!!.concent,
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(top = 100.dp)
                                )
                                Text(
                                    text = "Tipo: " + selectedProduct.value!!.type,
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(top = 150.dp)
                                )
                                Text(
                                    text = "Presentacion: " + selectedProduct.value!!.presentation,
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(top = 200.dp)
                                )
                                Button(
                                    onClick = {
                                        drugRepository.deleteDrug(selectedProduct.value!!.id)
                                        { result ->
                                            if (result is Result.Success) {
                                                navController.navigate(Route.Drug.route)
                                            } else {
                                                Toast.makeText(context, result.message.toString(),
                                                    Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                        }
                                    },
                                    modifier = Modifier.align(Alignment.BottomCenter)                                  )
                                {
                                    Text(text = "Delete")
                                }
                            }
                        }
                    }
                }
            }
        }

        // Circular button at the bottom right corner
        Box(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Route.AddDrugs.route)
                },
                modifier = Modifier
                    .size(56.dp),
                containerColor = Color(57, 192, 224)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DrugListPreview() {
    MedCareTheme {
        DrugList(navController = rememberNavController())
    }
}