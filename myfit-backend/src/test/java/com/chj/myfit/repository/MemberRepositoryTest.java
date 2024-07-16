package com.chj.myfit.repository;


import com.chj.myfit.entity.Inbody;
import com.chj.myfit.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testSave(){
        Member member = new Member();
        member.setName("memberA");
        memberRepository.save(member);

        Inbody inbody1 = new Inbody();
        inbody1.setMeasurementDate(LocalDateTime.now());
        inbody1.setMember(member);

        Inbody inbody2 = new Inbody();
        inbody2.setMeasurementDate(LocalDateTime.now());
        inbody2.setMember(member);

        em.persist(inbody1);
        em.persist(inbody2);

        em.clear();

        Member findMember = em.find(Member.class, 1L);
        List<Inbody> inbodyList = findMember.getInbodies();
        for (Inbody inbody : inbodyList) {
            System.out.println("inbody = " + inbody.getId());
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testSelect(){

        Member findMember = em.find(Member.class, 1L);
        List<Inbody> inbodyList = findMember.getInbodies();
        for (Inbody inbody : inbodyList) {
            System.out.println("inbody = " + inbody.getId());
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate(){
        Member member1 = new Member();
        member1.setName("memberA");

        Member member2 = new Member();
        member2.setName("memberB");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Inbody inbody = new Inbody();
        inbody.setMember(member1);

        em.persist(inbody);

        em.flush();;
        Long inbodyId = inbody.getId();

        em.clear();

        Inbody inbody1 = em.find(Inbody.class, inbodyId);
        inbody1.setMember(member2);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
        Member member = new Member();
        member.setName("memberA");
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testMember2() {
        Member member = new Member();
        member.setName("memberA");
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장
    }
}