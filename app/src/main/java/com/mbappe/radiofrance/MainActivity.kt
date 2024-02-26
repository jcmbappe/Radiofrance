package com.mbappe.radiofrance

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.mbappe.common.ApiResponse
import com.mbappe.models.Brand
import com.mbappe.radiofrance.ui.theme.RadioFranceTheme
import com.mbappe.repositories.BrandsRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: MainViewModel by viewModels()

        setContent {
            val list by viewModel.state.collectAsStateWithLifecycle()

            RadioFranceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn() {
                        items(items = list) {
                            Greeting(
                                name = "${it.id} ${it.title}",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BrandsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<List<Brand>>(listOf())
    val state: StateFlow<List<Brand>> = _state

    init {
        getBrands()
    }

    private fun getBrands() {
        viewModelScope.launch {
            repository.getBrands()
                .onEach { apiResponse ->
                    when (apiResponse) {
                        is ApiResponse.Error -> Log.d("JC", "Error ${apiResponse.message}")
                        is ApiResponse.Success -> apiResponse.data?.let { _state.emit(it) }
                    }
                }.launchIn(this)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RadioFranceTheme {
        Greeting("Android")
    }
}