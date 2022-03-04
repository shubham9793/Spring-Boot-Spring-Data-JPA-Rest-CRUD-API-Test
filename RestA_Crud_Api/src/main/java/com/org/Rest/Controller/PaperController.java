package com.org.Rest.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.Rest.Model.PaperModel;
import com.org.Rest.Repository.PaperRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PaperController {
  @Autowired
  PaperRepository paperRepository;
  @GetMapping("/papers")
  public ResponseEntity<List<PaperModel>> getAllTutorials(@RequestParam(required = false) String title) {
    try {
      List<PaperModel> papermodels = new ArrayList<PaperModel>();
      if (title == null)
    	  paperRepository.findAll().forEach(papermodels::add);
      else
    	  paperRepository.findByTitleContaining(title).forEach(papermodels::add);
      if (papermodels.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(papermodels, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/papers/{id}")
  public ResponseEntity<PaperModel> getTutorialById(@PathVariable("id") long id) {
    Optional<PaperModel> tutorialData = paperRepository.findById(id);
    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/papers")
  public ResponseEntity<PaperModel> createTutorial(@RequestBody PaperModel papermodel) {
    try {
    	PaperModel pm = paperRepository
          .save(new PaperModel(papermodel.getTitle(), papermodel.getDescription(), false));
      return new ResponseEntity<>(pm, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/papers/{id}")
  public ResponseEntity<PaperModel> updateTutorial(@PathVariable("id") long id, @RequestBody PaperModel papermodel) {
    Optional<PaperModel> tutorialData = paperRepository.findById(id);
    if (tutorialData.isPresent()) {
    	PaperModel pm = tutorialData.get();
      pm.setTitle(papermodel.getTitle());
      pm.setDescription(papermodel.getDescription());
      pm.setPublished(papermodel.isPublished());
      return new ResponseEntity<>(paperRepository.save(pm), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/papers/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    try {
    	paperRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @DeleteMapping("/papers")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
    	paperRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/papers/published")
  public ResponseEntity<List<PaperModel>> findByPublished() {
    try {
      List<PaperModel> papermodels = paperRepository.findByPublished(true);
      if (papermodels.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(papermodels, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}