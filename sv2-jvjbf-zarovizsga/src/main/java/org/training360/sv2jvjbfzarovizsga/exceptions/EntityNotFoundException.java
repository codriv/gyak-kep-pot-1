package org.training360.sv2jvjbfzarovizsga.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class EntityNotFoundException extends AbstractThrowableProblem {

    public EntityNotFoundException(String entity, long id) {
        super(
                URI.create("/api/schools/entity-not-found"),
                "Entity not found",
                Status.NOT_FOUND,
                String.format("%s not found with id: %d", entity, id));
    }
}
