package com.eseo.noteapp.view.activity

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eseo.noteapp.NoteApplication
import com.eseo.noteapp.R
import com.eseo.noteapp.databinding.ActivityUpdateBinding
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.viewmodel.NoteViewModel
import com.eseo.noteapp.viewmodel.NoteViewModelFactory

/**
 * Activité pour mettre à jour une note.
 */
class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    /**
     * Méthode appelée à la création de l'activité.
     *
     * @param savedInstanceState L'état sauvegardé de l'application.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)

        val note : Note = intent.getSerializableExtra("UPDATE") as Note
        binding.titleEdit.setText(note.title)
        binding.textEdit.setText(note.text)

        /**
         * Définit l'action à effectuer lors du clic sur le bouton de confirmation.
         */
        binding.confirmButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.titleEdit.text) || TextUtils.isEmpty(binding.textEdit.text)) {
                Toast.makeText(
                    applicationContext,
                    "Le titre ou le texte ne peuvent pas être vides",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                note.title = binding.titleEdit.text.toString()
                note.text = binding.textEdit.text.toString()
                noteViewModel.updateNote(note)
                finish()
            }
        }

    }
}