import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ideavista.presentation.view.theme.Amarillo
import com.example.ideavista.presentation.view.theme.NegroClaro
import com.example.ideavista.presentation.view.theme.Violeta
import com.example.ideavista.R
import com.example.ideavista.presentation.event.UIEvent
import com.example.ideavista.presentation.state.LoginStep
import com.example.ideavista.presentation.view.composable.loginComposables.AlreadyUserContent
import com.example.ideavista.presentation.view.composable.loginComposables.LoginContent
import com.example.ideavista.presentation.view.composable.loginComposables.RegisterContent
import com.example.ideavista.presentation.viewmodel.LoginScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel = koinViewModel(),
    navHostController: NavHostController
) {
    val state by viewModel.uiState

    val uiEvent by viewModel.uiEvent.collectAsState()

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            is UIEvent.NavigateToHome -> {
                navHostController.navigate("main") // Navegamos a la vista Home
                viewModel.resetUiEvent() // Reseteamos el evento
            }

            is UIEvent.ShowError -> {
                val message = (uiEvent as UIEvent.ShowError).message
                viewModel.resetUiEvent() // Reseteamos el evento
            }

            null -> Unit // No hacer nada si el evento es nulo
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Inicia sesión o regístrate",
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    )
                },
                navigationIcon = {
                    if (state.step != LoginStep.Login) {
                        IconButton(onClick = { viewModel.resetToLogin() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    TextButton(onClick = { navHostController.navigate("main") }) {
                        Text(
                            "Ahora no",
                            color = Violeta,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Amarillo)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (state.step) {
                LoginStep.Login -> LoginContent(
                    email = state.email,
                    onEmailEntered = { viewModel.onEmailChange(it) },
                    onContinue = { viewModel.onContinueClick() },
                    onGoogleSignIn = { viewModel.onGoogleSignIn() }
                )

                LoginStep.AlreadyUser -> AlreadyUserContent(
                    email = state.email,
                    onPasswordEntered = { viewModel.onPasswordEntered(it) },
                    onForgotPassword = { viewModel.onForgotPassword() },
                    onLogin = { viewModel.login(state.email, state.password) } // Llamando a `login`
                )

                LoginStep.Register -> RegisterContent(
                    email = state.email,
                    onEmailConfirmed = { viewModel.onEmailConfirmed(it) },
                    onRegister = { email, password -> viewModel.register(email, password) }
                )
            }
        }
    }
}
