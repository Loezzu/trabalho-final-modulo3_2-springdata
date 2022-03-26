package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.PersonInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonInfoRepository extends JpaRepository<PersonInfoEntity, Integer> {

    @Query("select pi from personinfo pi inner join like_tindev_user ltu " +
            "on pi.idPersonInfo = ltu.likedUserId where (:id) = ltu.userId")
    List<PersonInfoEntity> listLikesById (Integer id);


    @Query("select pi from personinfo pi inner join like_tindev_user ltu " +
            "on pi.idPersonInfo = ltu.userId where ltu.likedUserId = (:id)")
    List<PersonInfoEntity> listReceivedLikesById (Integer id);







//    private static List<PersoInfo> persoInfoList = new ArrayList<>();
//    private AtomicInteger COUNTER = new AtomicInteger();
//
//    public PersoInfoRepository() {
//        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "João da Silva", 20, "joaozinho@mail.com"));
//        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "Pedro da Silva", 20, "pedrinho@mail.com"));
//        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "Lucas da Silva", 20, "luquinhas@mail.com"));
//        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "Guilherme da Silva", 20, "gui@mail.com"));
//        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "Rafael da Silva", 20, "rafa@mail.com"));
//    }
//
//   public PersoInfo create(PersoInfo persoInfo) {
//        persoInfo.setIdPersoInfo(COUNTER.incrementAndGet());
//        persoInfoList.add(persoInfo);
//        return persoInfo;
//   }
//
//   public List<PersoInfo> list() {
//       return persoInfoList;
//   }
//
//   public PersoInfo update(Integer id, PersoInfo persoInfoUpdate) throws Exception {
//        PersoInfo persoInfoUpdated = persoInfoList.stream()
//                .filter(persoInfo -> persoInfo.getIdPersoInfo().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RegraDeNegocioException("PersoInfo not found"));
//        persoInfoUpdated.setRealName(persoInfoUpdate.getRealName());
//        persoInfoUpdated.setAge(persoInfoUpdate.getAge());
//        persoInfoUpdated.setEmail(persoInfoUpdate.getEmail());
//        return persoInfoUpdated;
//   }
//
//   public void delete(Integer id) throws Exception {
//        PersoInfo persoInfoDeleted = persoInfoList.stream()
//                .filter(persoInfo -> persoInfo.getIdPersoInfo().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RegraDeNegocioException("PersoInfo not found"));
//        persoInfoList.remove(persoInfoDeleted);
//   }
//
//    public boolean getByEmail(String email) {
//        return persoInfoList.stream()
//                .anyMatch(persoInfo -> persoInfo.getEmail().equals(email));
//    }
//
//    public PersoInfo getPersoInfoById(Integer id) throws RegraDeNegocioException {
//        return persoInfoList.stream()
//                .filter(persoInfo -> persoInfo.getIdPersoInfo().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RegraDeNegocioException("PersoInfo not found"));
//    }
}
