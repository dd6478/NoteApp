package com.eseo.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eseo.noteapp.R
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.databinding.ActivityAddBinding

/**
 * Activité pour ajouter une note.
 */
class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    /**
     * Méthode appelée à la création de l'activité.
     *
     * @param savedInstanceState L'état sauvegardé de l'application.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add )

        /**
         * Définit l'action à effectuer lors du clic sur le bouton de confirmation.
         */
        binding.confirmButton.setOnClickListener {

            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.titleEdit.text) || TextUtils.isEmpty(binding.textEdit.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {

                val note = Note(
                    title = binding.titleEdit.text.toString(),
                    text = binding.textEdit.text.toString()
                )

                replyIntent.putExtra(EXTRA_REPLY, note)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        /**
         * Constante pour la clé de l'intent de réponse.
         */
        val EXTRA_REPLY: String = "com.eseo.android.noteapp.REPLY"
    }
}