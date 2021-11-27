package com.example.simbirsoftbackendpractice.service;

import com.example.simbirsoftbackendpractice.domain.Room;
import com.example.simbirsoftbackendpractice.domain.User;
import com.example.simbirsoftbackendpractice.exception.NoRightException;
import com.example.simbirsoftbackendpractice.repository.RoomRepo;
import com.example.simbirsoftbackendpractice.repository.RoomTypeRepo;
import com.example.simbirsoftbackendpractice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    private final String INVALID_SYNTAX = "There's an error in command syntax. Type //help to see the list of commands";

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoomTypeRepo roomTypeRepo;

    @Autowired
    private RoomRepo roomRepo;

    public String executeCommand(String command, Long user_id) throws NoRightException {
        String[] commandParts = command.split("\\s+");
        if (commandParts.length < 1) return INVALID_SYNTAX;
        switch (commandParts[0]) {
            case "//room": return executeRoomCommand(commandParts, user_id);
            case "//user": return executeUserCommand(commandParts, user_id);
            case "//yBot": return executeYBotCommand(commandParts, user_id);
            case "//help": return executeHelpCommand();
            default: return INVALID_SYNTAX;
        }
    }

    private String executeRoomCommand(String[] command, Long user_id) throws NoRightException {
        if (command.length < 2) return INVALID_SYNTAX;
        switch (command[1]) {
            case "create": return executeRoomCreateCommand(command, user_id);
            case "remove": return executeRoomRemoveCommand(command, user_id);
            case "rename": return executeRoomRenameCommand(command, user_id);
            case "connect": return executeRoomConnectCommand(command, user_id);
            case "disconnect": return executeRoomDisconnectCommand(command, user_id);
            default: return INVALID_SYNTAX;
        }
    }

    private String executeRoomCreateCommand(String[] command, Long user_id) throws NoRightException {
        if (command.length == 3) {
            Room room = new Room(command[2], userRepo.getById(user_id), roomTypeRepo.getRoomTypeByName("PUBLIC"));
            roomService.createRoom(room, user_id);
            return "Public room is created";
        }
        if (command.length == 4 && command[2].equals("-c")) {
            Room room = new Room(command[3], userRepo.getById(user_id), roomTypeRepo.getRoomTypeByName("PRIVATE"));
            roomService.createRoom(room, user_id);
            return "Private room is created";
        }
        return INVALID_SYNTAX;
    }

    private String executeRoomRemoveCommand(String[] command, Long user_id) throws NoRightException {
        if (command.length == 3) {
            Long id = roomRepo.getByName(command[2]).getId();
            roomService.removeRoom(id, user_id);
            return "Room is removed";
        }
        return INVALID_SYNTAX;
    }

    private String executeRoomRenameCommand(String[] command, Long user_id) throws NoRightException {
        if (command.length == 4) {
            Long id = roomRepo.getByName(command[2]).getId();
            roomService.updateRoomName(id, command[3], user_id);
            return "Room is removed";
        }
        return INVALID_SYNTAX;
    }

    private String executeRoomConnectCommand(String[] command, Long user_id) throws NoRightException {
        if (command.length == 3) {
            Room room = roomRepo.getByName(command[2]);
            roomService.addUserInRoom(room.getId(), user_id, user_id);
            return "User is added";
        }
        if (command.length == 5 && command[3].equals("-l")) {
            User user = userRepo.getUserByLogin(command[4]);
            Room room = roomRepo.getByName(command[2]);
            roomService.addUserInRoom(room.getId(), user.getId(), user_id);
            return "User is added";
        }
        return INVALID_SYNTAX;
    }

    private String executeRoomDisconnectCommand(String[] command, Long user_id) {
        if (command.length == 2) {

        }
        return "OK";
    }

    private String executeUserCommand(String[] command, Long user_id) {
        if (command.length < 2) return INVALID_SYNTAX;
        switch (command[1]) {
            case "rename": return executeUserRenameCommand(command, user_id);
            case "ban": return executeUserBanCommand(command, user_id);
            case "moderator": return executeUserModeratorCommand(command, user_id);
            default: return INVALID_SYNTAX;
        }
    }

    private String executeUserRenameCommand(String[] command, Long user_id) {
        return "OK";
    }

    private String executeUserBanCommand(String[] command, Long user_id) {
        return "OK";
    }

    private String executeUserModeratorCommand(String[] command, Long user_id) {
        return "OK";
    }

    private String executeYBotCommand(String[] command, Long user_id) {
        return "Isn't available now";
    }

    private String executeHelpCommand() {
        return "Комнаты:\n" +
                "1. //room create {Название комнаты} - создает комнаты;\n" +
                "\t-c закрытая комната. Только (владелец, модератор и админ) может\n" +
                "\tдобавлять/удалять пользователей из комнаты.\n" +
                "2. //room remove {Название комнаты} - удаляет комнату (владелец и админ);\n" +
                "3. //room rename {Название комнаты} - переименование комнаты (владелец и\n" +
                "\tадмин);\n" +
                "4. //room connect {Название комнаты} - войти в комнату;\n" +
                "\t-l {login пользователя} - добавить пользователя в комнату\n" +
                "5. //room disconnect - выйти из текущей комнаты;\n" +
                "6. //room disconnect {Название комнаты} - выйти из заданной комнаты;\n" +
                "\t-l {login пользователя} - выгоняет пользователя из комнаты (для владельца,\n" +
                "\tмодератора и админа).\n" +
                "\t-m {Количество минут} - время на которое пользователь не сможет войти (для\n" +
                "\tвладельца, модератора и админа).\n" +
                "Пользователи:\n" +
                "1. //user rename {login пользователя} (владелец и админ);\n" +
                "2. //user ban;\n" +
                "\t-l {login пользователя} - выгоняет пользователя из всех комнат\n" +
                "\t-m {Количество минут} - время на которое пользователь не сможет войти.\n" +
                "3. //user moderator {login пользователя} - действия над модераторами.\n" +
                "\t-n - назначить пользователя модератором.\n" +
                "\t-d - “разжаловать” пользователя.\n" +
                "Боты:\n" +
                "1. //yBot find -k -l {название канала}||{название видео} - в ответ бот присылает\n" +
                "\tссылку на ролик;\n" +
                "\t-v - выводит количество текущих просмотров.\n" +
                "\t-l - выводит количество лайков под видео.\n" +
                "2. //yBot help - список доступных команд для взаимодействия.\n" +
                "Другие:\n" +
                "1. //help - выводит список доступных команд.";
    }
}
