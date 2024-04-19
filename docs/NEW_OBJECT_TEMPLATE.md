# New Object Template

## Domain

### `domain/Object`

```Java
import jakarta.persistence.Entity;

@Entity
public class ObjectNameHere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ADD INSTANCE VARIABLES HERE

}

// ADD GETTERS AND SETTERS HERE

// ADD ENUMS HERE (IF NEEDED)

// ADD TO STRING HERE

```

## Repository

### `repository/ObjectRepository`

```Java
package com.coderscampus.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<ObjectNameHere, Long> {
}

```

## Service

### `service/ObjectService`

```Java


```



