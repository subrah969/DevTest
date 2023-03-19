package com.thevirtugroup.postitnote.rest;

import com.thevirtugroup.postitnote.exception.ResourceNotFoundException;
import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 */
@RestController
@RequestMapping("/api")
public class NoteController
{
    @Autowired
    NoteRepository noteRepository;

    // Get All Notes
    @RequestMapping(value = "notes", method = RequestMethod.GET)
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }


    // Create a new Note
    @RequestMapping(value = "notes", method = RequestMethod.POST)
    public Note createNote(@Validated @RequestBody Note note) {
        return noteRepository.save(note);
    }

    // Update a Note
    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    public Note updateNote(@PathVariable(value = "id") Long noteId, @Validated @RequestBody Note noteDetails) {

        Note note = noteRepository.findOne(noteId);
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

}
