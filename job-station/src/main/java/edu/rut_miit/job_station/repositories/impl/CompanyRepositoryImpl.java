package edu.rut_miit.job_station.repositories.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import edu.rut_miit.job_station.dto.company.CompanyRatingDto;
import edu.rut_miit.job_station.entities.Company;
import edu.rut_miit.job_station.repositories.CompanyRepository;

@Repository
public class CompanyRepositoryImpl extends BaseRepository<Company, String> implements CompanyRepository {
    public CompanyRepositoryImpl() {
        super(Company.class);
    }

    @Override
    public List<Company> findByCreatorId(String creatorId) {
        return entityManager().createQuery("""
                    select c from Company c
                    join c.creator cr
                    where cr.id = :creator_id
                """, Company.class)
                .setParameter("creator_id", creatorId)
                .getResultList();
    }

    @Override
    public Optional<Company> findByName(String name) {
        try {
            return Optional.ofNullable(
                entityManager().createQuery("""
                    select c from Company c
                    where c.name = :name
                """, Company.class)
                .setParameter("name", name)
                .getSingleResult()
            );
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CompanyRatingDto> findSortedByApplicationsAmountDesc(int limit) {
        return entityManager().createNativeQuery("""
                select companies.id, companies.name, companies.description, count(companies.id) amount
                from companies
                join vacancies on vacancies.company_id = companies.id
                join applications on applications.vacancy_id = vacancies.id
                group by companies.id
                order by count(companies.id) desc
                limit :limit
            """, CompanyRatingDto.class)
            .setParameter("limit", limit)
            .getResultList();
    }
}
