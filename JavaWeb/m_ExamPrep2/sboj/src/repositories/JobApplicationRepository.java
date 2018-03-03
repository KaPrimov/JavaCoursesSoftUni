package repositories;

import entities.JobApplication;

import java.util.ArrayList;
import java.util.List;

public class JobApplicationRepository extends BaseRepository {
    public void createJobApplication(JobApplication jobApplication) {
        this.execute(actionResult -> {
            this.entityManager.persist(jobApplication);
        });
    }

    public List<JobApplication> findAll() {
        List<JobApplication> result = new ArrayList<JobApplication>();

        this.execute(actionResult -> {
            result.addAll(this.entityManager.createNativeQuery(
                    "SELECT * FROM job_applications", JobApplication.class
            ).getResultList());
        });

        return result;
    }
    public JobApplication findById(String jobApplicationId) {
        JobApplication result = (JobApplication) this.execute(actionResult -> {
            actionResult.setActionResult(
                    this.entityManager
                            .createNativeQuery(
                                    "SELECT * FROM job_applications AS ja WHERE ja.id = \'" + jobApplicationId + "\'", JobApplication.class)
                            .getResultList()
                            .stream()
                            .findFirst()
                            .orElse(null));
        }).getActionResult();

        return result;
    }

    public void deleteById(String jobApplicationId) {
        this.execute(actionResult -> {
            JobApplication jobApplication = (JobApplication)

            this.entityManager
                    .createNativeQuery(
                            "SELECT * FROM job_applications AS ja WHERE ja.id = \'" + jobApplicationId + "\'", JobApplication.class)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            this.entityManager.remove(jobApplication);
        });
    }
}
