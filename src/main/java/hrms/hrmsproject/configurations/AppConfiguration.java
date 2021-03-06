package hrms.hrmsproject.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import hrms.hrmsproject.core.utilities.uploads.CloudinaryManager;
import hrms.hrmsproject.core.utilities.uploads.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinaryUser() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "canozyigitt",
                "api_key", "915623766256217",
                "api_secret", "O9GDJkYg7U832Oeu14_XUqeYFaI"));
    }

    @Bean
    public FileService fileService() {
        return new CloudinaryManager(cloudinaryUser());
    }
}
