INSERT INTO `user` (`id`,`username`, `password`, `enabled`) VALUES (1,'john', '123', '1');
INSERT INTO `user` (`id`,`username`, `password`, `enabled`) VALUES (2,'doe', '123', '1');
INSERT INTO `user` (`id`,`username`, `password`, `enabled`) VALUES (3,'hemant', '123', '1');

INSERT INTO `role` (`id`,`name`) VALUES (1,'USER');
INSERT INTO `role` (`id`,`name`) VALUES (2,'MANAGER');
INSERT INTO `role` (`id`,`name`) VALUES (3,'ADMIN');

INSERT INTO `privilige` (`id`,`name`) VALUES (1,'ROLE_USER');
INSERT INTO `privilige` (`id`,`name`) VALUES (2,'ROLE_MANGER');
INSERT INTO `privilige` (`id`,`name`) VALUES (3,'ROLE_ADMIN');



INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (1,2);
INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (2,3);
INSERT INTO `users_roles` (`user_id`,`role_id`) VALUES (3,3);


INSERT INTO `roles_privileges` (`role_id`,`privilege_id`) VALUES (1,1);
INSERT INTO `roles_privileges` (`role_id`,`privilege_id`) VALUES (2,2);
INSERT INTO `roles_privileges` (`role_id`,`privilege_id`) VALUES (3,3);