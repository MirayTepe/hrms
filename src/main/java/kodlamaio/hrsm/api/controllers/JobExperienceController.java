package kodlamaio.hrsm.api.controllers;

import kodlamaio.hrsm.business.abstracts.cv.JobExperienceService;
import kodlamaio.hrsm.core.utilities.results.ErrorDataResult;
import kodlamaio.hrsm.entities.concretes.cv.JobExperience;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/jobExperience")

public class JobExperienceController {
    private JobExperienceService jobExperienceService;

    public JobExperienceController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }


    @PostMapping(value = "/addJobExperience")
    public ResponseEntity<?> add(@Valid @RequestBody JobExperience jobExperience) {
        return ResponseEntity.ok(this.jobExperienceService.add(jobExperience));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors=new HashMap<String,String>();
        for (FieldError fieldError:exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors=new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
        return  errors;
    }
}
