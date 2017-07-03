package bzh.zomzog.zeshop.product.service;

import bzh.zomzog.zeshop.product.config.StorageProperties;
import bzh.zomzog.zeshop.product.exception.StorageException;
import bzh.zomzog.zeshop.product.exception.StorageFileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Zomzog on 30/06/2017.
 */
@Service
public class StorageService {
    private final Logger log = LoggerFactory.getLogger(StorageService.class);

    private final Path rootLocation;

    public StorageService(final StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public void store(final MultipartFile file, final String fileName) throws StorageException {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
        } catch (final IOException e) {
            this.log.error("Failed to store file " + file.getOriginalFilename(), e);
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    public Stream<Path> loadAll() throws StorageException {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (final IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    public Path load(final String filename) {
        return this.rootLocation.resolve(filename);
    }

    public Resource loadAsResource(final String filename) throws StorageFileNotFoundException {
        try {
            final Path file = load(filename);
            final Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (final MalformedURLException e) {
            this.log.error("Could not read file: " + filename, e);
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public void delete(final String fileName) throws StorageException {
        try {
            Files.delete(this.rootLocation.resolve(fileName));
        } catch (final IOException e) {
            this.log.error("Failed to delete file " + fileName, e);
            throw new StorageException("Failed to delete file " + fileName, e);
        }
    }
}
