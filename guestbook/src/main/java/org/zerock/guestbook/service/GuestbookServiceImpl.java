package org.zerock.guestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.repository.GuestbookRepository;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;

    @Override
    public Long register(GuestbookDTO dto) {

        log.info("DTO------------------");
        log.info(dto);
        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getId();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Guestbook> result = repository.findAll(pageable);
        Function<Guestbook, GuestbookDTO> fn = (entity ->
                entityToDto(entity));


        return new PageResultDTO<>(result, fn);
    }

    @Override
    public GuestbookDTO read(Long id) {
        Guestbook result = repository.findById(id).orElse(null);
        return result != null ? entityToDto(result): null;
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Guestbook entity = repository.findById(dto.getId()).orElseThrow(RuntimeException::new);

        entity.changeTitle(dto.getTitle());

        entity.changeContent(dto.getContent());

        repository.save(entity);

    }


}
