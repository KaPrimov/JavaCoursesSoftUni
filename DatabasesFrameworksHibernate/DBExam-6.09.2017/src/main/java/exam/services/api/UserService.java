package exam.services.api;

import exam.dto.binding.json.FollowerDataImportJsonDto;
import exam.dto.binding.json.UserImportFromJsonDto;
import exam.dto.view.json.PopularUserJsonDto;
import exam.dto.view.xml.MostCommentsXmlWrapper;
import exam.entities.User;

import java.util.List;

public interface UserService {
    User importUser(UserImportFromJsonDto userDto);
    boolean registerFollower(FollowerDataImportJsonDto followerData);
    public List<PopularUserJsonDto> returnAllPopularUsers();
    MostCommentsXmlWrapper findUsersMostComments();
}
