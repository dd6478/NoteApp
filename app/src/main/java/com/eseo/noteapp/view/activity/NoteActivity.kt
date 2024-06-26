package com.eseo.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.eseo.noteapp.NoteApplication
import com.eseo.noteapp.R
import com.eseo.noteapp.databinding.ActivityNoteBinding
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.view.adapter.NoteAdapter
import com.eseo.noteapp.viewmodel.NoteViewModel
import com.eseo.noteapp.viewmodel.NoteViewModelFactory

/**
 * Activité pour afficher les notes.
 */
class NoteActivity : AppCompatActivity() {

    private val newNoteActivityRequestCode = 1

    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    private lateinit var binding: ActivityNoteBinding
    private lateinit var noteAdapter: NoteAdapter

    /**
     * Méthode appelée à la création de l'activité.
     *
     * @param savedInstanceState L'état sauvegardé de l'application.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note)

        noteAdapter = NoteAdapter(noteViewModel)

        setNoteRecycler()

        noteViewModel.notesLiveData.observe(this, Observer { notes ->
            noteAdapter.loadNotes(notes)
        })

        binding.addNewNote.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }

    /**
     * Configure le RecyclerView pour afficher les notes.
     */
    private fun setNoteRecycler() {
        binding.noteRecycler.apply {
            adapter = noteAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
            setHasFixedSize(true)
        }
    }

    /**
     * Méthode appelée lorsqu'une activité lancée avec startActivityForResult se termine.
     *
     * @param requestCode Le code de requête passé à startActivityForResult.
     * @param resultCode Le code de résultat renvoyé par l'activité enfant.
     * @param intentData Un Intent qui peut renvoyer des données de résultat à l'appelant.
     */
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val note =  intentData?.getSerializableExtra(AddActivity.EXTRA_REPLY) as Note
            noteViewModel.insertNote(note)

        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}