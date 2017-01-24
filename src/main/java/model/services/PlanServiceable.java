package model.services;

import model.entities.Plan;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface PlanServiceable extends GenericService<Plan> {
    List<Optional<Plan>> getAll() throws SQLException;
}
