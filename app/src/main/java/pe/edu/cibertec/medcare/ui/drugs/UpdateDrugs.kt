package pe.edu.cibertec.medcare.ui.drugs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.cibertec.medcare.data.model.Drug

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDrugs(navController: NavController, drug: Drug, onUpdate: (Drug) -> Unit) {
    // Define local state to hold the updated values
    val updatedName = remember { mutableStateOf(drug.name) }
    val updatedConcent = remember { mutableStateOf(drug.concent) }
    val updatedType = remember { mutableStateOf(drug.type) }
    val updatedPresentation = remember { mutableStateOf(drug.presentation) }
    val updatedImage = remember { mutableStateOf(drug.image) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display form fields for updating the data
        TextField(
            value = updatedName.value,
            onValueChange = { updatedName.value = it },
            label = { Text("Name") }
        )
        TextField(
            value = updatedConcent.value,
            onValueChange = { updatedConcent.value = it },
            label = { Text("Concentration") }
        )
        TextField(
            value = updatedType.value,
            onValueChange = { updatedType.value = it },
            label = { Text("Type") }
        )
        TextField(
            value = updatedPresentation.value,
            onValueChange = { updatedPresentation.value = it },
            label = { Text("Presentation") }
        )
        TextField(
            value = updatedImage.value,
            onValueChange = { updatedImage.value = it },
            label = { Text("Image") }
        )

        // Update button
        Button(
            onClick = {
                // Create a new Drug object with the updated values
                val updatedDrug = Drug(
                    id = drug.id,
                    name = updatedName.value,
                    concent = updatedConcent.value,
                    type = updatedType.value,
                    presentation = updatedPresentation.value,
                    image = updatedImage.value
                )
                // Invoke the onUpdate callback with the updated drug
                onUpdate(updatedDrug)
            }
        ) {
            Text("Update")
        }
    }
}
