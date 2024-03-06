package org.eclipse.jakarta.hello.user.dao;

import org.eclipse.jakarta.hello.base.dao.BaseDAO;
import org.eclipse.jakarta.hello.user.dto.LoginRequestDTO;
import org.eclipse.jakarta.hello.user.entity.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Stateless
public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class);
    }
    public Optional<User> findUserByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("email"),email));
        return em.createQuery(cq).getResultList().stream().findFirst();
    }
}
