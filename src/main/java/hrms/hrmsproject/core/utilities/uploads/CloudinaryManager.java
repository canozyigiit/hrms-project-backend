package hrms.hrmsproject.core.utilities.uploads;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.ErrorDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryManager implements FileService{

    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryManager(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public DataResult<?> save(MultipartFile file) {
        try{
            Map cloudinaryUploader = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return new SuccessDataResult<Map>(cloudinaryUploader);
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return new ErrorDataResult<Map>();
    }

}
