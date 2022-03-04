package com.org.Rest.Repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.org.Rest.Model.PaperModel;


public interface PaperRepository extends JpaRepository<PaperModel, Long> {
	
	
	List<PaperModel> findByPublished(boolean published);
  
	List<PaperModel> findByTitleContaining(String title);
}