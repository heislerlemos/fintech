package ao.kwanzazap.fintech.Interface;
import ao.kwanzazap.fintech.Model.Movimento;
import org.hibernate.mapping.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MovimentoRepository  extends JpaRepository<Movimento, Long> {
    List<Movimento> findByContaId(Integer conta_id);


}
