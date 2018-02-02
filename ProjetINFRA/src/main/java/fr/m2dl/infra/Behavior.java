package fr.m2dl.infra;

import java.util.List;

public interface Behavior {
    List<Action> decide(LocalEnv env);
}
