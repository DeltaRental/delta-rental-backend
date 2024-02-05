package com.delta.rental.deltarental.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.delta.rental.deltarental.core.services.CloudinaryService;
import com.delta.rental.deltarental.core.utilities.images.ImageUtils;
import com.delta.rental.deltarental.entities.concretes.Image;
import com.delta.rental.deltarental.repositories.ImageRepository;
import com.delta.rental.deltarental.services.abstracts.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageDataManager implements ImageService {

    private final ImageRepository dataRepository;
    private final CloudinaryService cloudinaryService;
    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Image imageData = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build();

        Map<String, String> params = ObjectUtils.asMap(
                "use_filename", file.getName(),
                "unique_filename", true,
                "overwrite", true
        );

        imageData.setImageUrl(cloudinary.uploader().upload(file.getBytes(), params)
                .get("url")
                .toString());
        dataRepository.save(imageData);
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return "file uploaded Fail ";

    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = dataRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;

    }
}
