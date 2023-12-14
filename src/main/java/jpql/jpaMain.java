package jpql;

import javax.persistence.*;

public class jpaMain {

    public static void main(String[] args) {
        // 하나만 생성하여 애플리케이션 전체 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 쓰레드마다 공유 X , 사용하고 버리기
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

           /* // 반환 타입 명확 | TypedQuery
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class); // m.username 은 String 타입
            // 반환 타입 명확 X | Query
            Query query3 = em.createQuery("select m.username, m.age from Member m"); // m.username 과 m.age는 String, int 타입

            Member result = query1.getSingleResult();
            System.out.println("result = " + result);*/

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 영속성 콘텍스트 종료
            em.close();
        }

        emf.close();
    }

}
