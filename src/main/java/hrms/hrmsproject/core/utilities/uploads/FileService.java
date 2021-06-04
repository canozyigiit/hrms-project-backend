package hrms.hrmsproject.core.utilities.uploads;

import hrms.hrmsproject.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    DataResult<?> save(MultipartFile file);
}
