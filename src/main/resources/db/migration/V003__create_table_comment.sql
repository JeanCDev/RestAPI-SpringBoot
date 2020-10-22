create table osworks.comment(
	id bigint not null auto_increment,
    service_order_id bigint not null,
    description text not null,
    send_date datetime,
    
    primary key(id)
);

alter table osworks.comment add constraint fk_service_order_comment
foreign key (service_order_id) references osworks.service_order(id)
