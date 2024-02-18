package com.delta.rental.deltarental.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.delta.rental.deltarental.core.services.CloudinaryService;
import com.delta.rental.deltarental.core.utilities.images.ImageUtils;
import com.delta.rental.deltarental.entities.concretes.Image;
import com.delta.rental.deltarental.repositories.ImageRepository;
import com.delta.rental.deltarental.services.abstracts.ImageService;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static com.delta.rental.deltarental.services.constants.Messages.ImageMessages.BASE_PUBLIC_ID;

@Service
@AllArgsConstructor
public class ImageDataManager implements ImageService {

    private final ImageRepository dataRepository;
    private final CloudinaryService cloudinaryService;
    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file,String plate) throws IOException {
        String publicId = Messages.ImageMessages.BASE_PUBLIC_ID + plate;
        Image imageData = Image.builder()
                .name(plate)
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build();

        Map<String, String> params = ObjectUtils.asMap(
                Messages.ImageMessages.USE_FILENAME, file.getName(),
                Messages.ImageMessages.UNIQUE_FILENAME, true,
                Messages.ImageMessages.OVERWRITE, true,
                Messages.ImageMessages.BASE_PUBLIC_ID,
                publicId
        );

        imageData.setImageUrl(cloudinary.uploader().upload(file.getBytes(), params)
                .get(Messages.ImageMessages.URL)
                .toString());
        dataRepository.save(imageData);
        if (imageData != null) {

            return imageData.getImageUrl();
        }
        return Messages.ImageMessages.FILE_UPLOADED_FAIL;

    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = dataRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;

    }
}

