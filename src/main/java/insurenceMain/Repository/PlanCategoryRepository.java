package insurenceMain.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import insurenceMain.Entity.PlanCategory;

public interface PlanCategoryRepository extends JpaRepository<PlanCategory, Serializable>{

}
