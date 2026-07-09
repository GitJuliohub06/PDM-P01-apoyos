package com.example.lvluptemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lvluptemplate.data.local.LvlUpDatabase
import com.example.lvluptemplate.navigation.AppNavigation
import com.example.lvluptemplate.ui.theme.LvlUPTemplateTheme
import com.example.lvluptemplate.ui.viewmodels.LvlUpViewModel
import com.example.lvluptemplate.ui.viewmodels.LvlUpViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val database = LvlUpDatabase.getDatabase(applicationContext)
            val dao = database.lvlUpDao()

            val viewModel: LvlUpViewModel = viewModel(
                factory = LvlUpViewModelFactory(dao)
            )

            LvlUPTemplateTheme() {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}