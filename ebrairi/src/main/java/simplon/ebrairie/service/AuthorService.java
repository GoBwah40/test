package simplon.ebrairie.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import simplon.ebrairie.domain.Author;
import simplon.ebrairie.model.AuthorDTO;
import simplon.ebrairie.repos.AuthorRepository;
import simplon.ebrairie.util.NotFoundException;


@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> findAll() {
        final List<Author> authors = authorRepository.findAll(Sort.by("idAuthor"));
        return authors.stream()
                .map(author -> mapToDTO(author, new AuthorDTO()))
                .toList();
    }

    public AuthorDTO get(final Integer idAuthor) {
        return authorRepository.findById(idAuthor)
                .map(author -> mapToDTO(author, new AuthorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final AuthorDTO authorDTO) {
        final Author author = new Author();
        mapToEntity(authorDTO, author);
        return authorRepository.save(author).getIdAuthor();
    }

    public void update(final Integer idAuthor, final AuthorDTO authorDTO) {
        final Author author = authorRepository.findById(idAuthor)
                .orElseThrow(NotFoundException::new);
        mapToEntity(authorDTO, author);
        authorRepository.save(author);
    }

    public void delete(final Integer idAuthor) {
        authorRepository.deleteById(idAuthor);
    }

    private AuthorDTO mapToDTO(final Author author, final AuthorDTO authorDTO) {
        authorDTO.setIdAuthor(author.getIdAuthor());
        authorDTO.setFisrtname(author.getFisrtname());
        authorDTO.setLastanme(author.getLastanme());
        authorDTO.setBio(author.getBio());
        return authorDTO;
    }

    private Author mapToEntity(final AuthorDTO authorDTO, final Author author) {
        author.setFisrtname(authorDTO.getFisrtname());
        author.setLastanme(authorDTO.getLastanme());
        author.setBio(authorDTO.getBio());
        return author;
    }

}
