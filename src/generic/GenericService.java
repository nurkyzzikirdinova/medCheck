package generic;

import model.Patient;

public interface GenericService <T> {

  //  GenericService : (Departmet, Doctor , Patient)


    String add(Long  hospitalId, T t);


    void removeById(Long id);

    String updateById(Long id, T t);


}
