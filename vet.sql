PGDMP         8                |            vet    13.13    13.13 G    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17274    vet    DATABASE     a   CREATE DATABASE vet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE vet;
                postgres    false            �            1259    17299    animals    TABLE     S  CREATE TABLE public.animals (
    animal_id integer NOT NULL,
    animal_breed character varying(255),
    animal_colour character varying(255),
    date_of_birth date,
    animal_gender character varying(255),
    animal_name character varying(255) NOT NULL,
    animal_species character varying(255),
    customer_id integer NOT NULL
);
    DROP TABLE public.animals;
       public         heap    postgres    false            �            1259    17297    animals_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.animals_animal_id_seq;
       public          postgres    false    201                       0    0    animals_animal_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.animals_animal_id_seq OWNED BY public.animals.animal_id;
          public          postgres    false    200            �            1259    17398    animals_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.animals_customer_id_seq;
       public          postgres    false    201                       0    0    animals_customer_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.animals_customer_id_seq OWNED BY public.animals.customer_id;
          public          postgres    false    212            �            1259    17384    appointments    TABLE     �   CREATE TABLE public.appointments (
    appointment_id integer NOT NULL,
    appointment_time timestamp(6) without time zone,
    animal_id integer NOT NULL,
    doctor_id integer NOT NULL
);
     DROP TABLE public.appointments;
       public         heap    postgres    false            �            1259    17408    appointments_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.appointments_animal_id_seq;
       public          postgres    false    209                       0    0    appointments_animal_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.appointments_animal_id_seq OWNED BY public.appointments.animal_id;
          public          postgres    false    213            �            1259    17382    appointments_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.appointments_appointment_id_seq;
       public          postgres    false    209                       0    0    appointments_appointment_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.appointments_appointment_id_seq OWNED BY public.appointments.appointment_id;
          public          postgres    false    208            �            1259    17415    appointments_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.appointments_doctor_id_seq;
       public          postgres    false    209                       0    0    appointments_doctor_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.appointments_doctor_id_seq OWNED BY public.appointments.doctor_id;
          public          postgres    false    214            �            1259    17392    available_dates    TABLE     �   CREATE TABLE public.available_dates (
    available_date_id integer NOT NULL,
    available_date_time date,
    doctor_id integer NOT NULL
);
 #   DROP TABLE public.available_dates;
       public         heap    postgres    false            �            1259    17390 %   available_dates_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_dates_available_date_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.available_dates_available_date_id_seq;
       public          postgres    false    211                       0    0 %   available_dates_available_date_id_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.available_dates_available_date_id_seq OWNED BY public.available_dates.available_date_id;
          public          postgres    false    210            �            1259    17422    available_dates_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_dates_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.available_dates_doctor_id_seq;
       public          postgres    false    211            	           0    0    available_dates_doctor_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.available_dates_doctor_id_seq OWNED BY public.available_dates.doctor_id;
          public          postgres    false    215            �            1259    17351 	   customers    TABLE       CREATE TABLE public.customers (
    customer_id integer NOT NULL,
    customer_address character varying(255),
    customer_city character varying(255),
    customer_mail character varying(255),
    customer_name character varying(255),
    customer_phone character varying(255)
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    17349    customers_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customers_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.customers_customer_id_seq;
       public          postgres    false    203            
           0    0    customers_customer_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.customers_customer_id_seq OWNED BY public.customers.customer_id;
          public          postgres    false    202            �            1259    17362    doctors    TABLE       CREATE TABLE public.doctors (
    doctor_id integer NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_mail character varying(255),
    doctor_name character varying(255),
    doctor_phone character varying(255)
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    17360    doctors_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.doctors_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.doctors_doctor_id_seq;
       public          postgres    false    205                       0    0    doctors_doctor_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.doctors_doctor_id_seq OWNED BY public.doctors.doctor_id;
          public          postgres    false    204            �            1259    17373    vaccines    TABLE     �   CREATE TABLE public.vaccines (
    vaccine_id integer NOT NULL,
    vaccine_code character varying(255),
    vaccine_name character varying(255),
    vaccine_finish_date date,
    vaccine_start_date date,
    animal_id integer NOT NULL
);
    DROP TABLE public.vaccines;
       public         heap    postgres    false            �            1259    17429    vaccines_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.vaccines_animal_id_seq;
       public          postgres    false    207                       0    0    vaccines_animal_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.vaccines_animal_id_seq OWNED BY public.vaccines.animal_id;
          public          postgres    false    216            �            1259    17371    vaccines_vaccine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vaccines_vaccine_id_seq;
       public          postgres    false    207                       0    0    vaccines_vaccine_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vaccines_vaccine_id_seq OWNED BY public.vaccines.vaccine_id;
          public          postgres    false    206            N           2604    17302    animals animal_id    DEFAULT     v   ALTER TABLE ONLY public.animals ALTER COLUMN animal_id SET DEFAULT nextval('public.animals_animal_id_seq'::regclass);
 @   ALTER TABLE public.animals ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    200    201    201            O           2604    17400    animals customer_id    DEFAULT     z   ALTER TABLE ONLY public.animals ALTER COLUMN customer_id SET DEFAULT nextval('public.animals_customer_id_seq'::regclass);
 B   ALTER TABLE public.animals ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    212    201            T           2604    17387    appointments appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointments_appointment_id_seq'::regclass);
 J   ALTER TABLE public.appointments ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    209    208    209            U           2604    17410    appointments animal_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN animal_id SET DEFAULT nextval('public.appointments_animal_id_seq'::regclass);
 E   ALTER TABLE public.appointments ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    213    209            V           2604    17417    appointments doctor_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN doctor_id SET DEFAULT nextval('public.appointments_doctor_id_seq'::regclass);
 E   ALTER TABLE public.appointments ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    214    209            W           2604    17395 !   available_dates available_date_id    DEFAULT     �   ALTER TABLE ONLY public.available_dates ALTER COLUMN available_date_id SET DEFAULT nextval('public.available_dates_available_date_id_seq'::regclass);
 P   ALTER TABLE public.available_dates ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    210    211    211            X           2604    17424    available_dates doctor_id    DEFAULT     �   ALTER TABLE ONLY public.available_dates ALTER COLUMN doctor_id SET DEFAULT nextval('public.available_dates_doctor_id_seq'::regclass);
 H   ALTER TABLE public.available_dates ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    215    211            P           2604    17354    customers customer_id    DEFAULT     ~   ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_customer_id_seq'::regclass);
 D   ALTER TABLE public.customers ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    202    203    203            Q           2604    17365    doctors doctor_id    DEFAULT     v   ALTER TABLE ONLY public.doctors ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctors_doctor_id_seq'::regclass);
 @   ALTER TABLE public.doctors ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    205    204    205            R           2604    17376    vaccines vaccine_id    DEFAULT     z   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccines_vaccine_id_seq'::regclass);
 B   ALTER TABLE public.vaccines ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    206    207    207            S           2604    17431    vaccines animal_id    DEFAULT     x   ALTER TABLE ONLY public.vaccines ALTER COLUMN animal_id SET DEFAULT nextval('public.vaccines_animal_id_seq'::regclass);
 A   ALTER TABLE public.vaccines ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    216    207            �          0    17299    animals 
   TABLE DATA           �   COPY public.animals (animal_id, animal_breed, animal_colour, date_of_birth, animal_gender, animal_name, animal_species, customer_id) FROM stdin;
    public          postgres    false    201   �U       �          0    17384    appointments 
   TABLE DATA           ^   COPY public.appointments (appointment_id, appointment_time, animal_id, doctor_id) FROM stdin;
    public          postgres    false    209   �V       �          0    17392    available_dates 
   TABLE DATA           \   COPY public.available_dates (available_date_id, available_date_time, doctor_id) FROM stdin;
    public          postgres    false    211   �V       �          0    17351 	   customers 
   TABLE DATA              COPY public.customers (customer_id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    203   W       �          0    17362    doctors 
   TABLE DATA           q   COPY public.doctors (doctor_id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    205   �W       �          0    17373    vaccines 
   TABLE DATA           ~   COPY public.vaccines (vaccine_id, vaccine_code, vaccine_name, vaccine_finish_date, vaccine_start_date, animal_id) FROM stdin;
    public          postgres    false    207   �W                  0    0    animals_animal_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.animals_animal_id_seq', 9, true);
          public          postgres    false    200                       0    0    animals_customer_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.animals_customer_id_seq', 1, true);
          public          postgres    false    212                       0    0    appointments_animal_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.appointments_animal_id_seq', 1, false);
          public          postgres    false    213                       0    0    appointments_appointment_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.appointments_appointment_id_seq', 3, true);
          public          postgres    false    208                       0    0    appointments_doctor_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.appointments_doctor_id_seq', 1, false);
          public          postgres    false    214                       0    0 %   available_dates_available_date_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.available_dates_available_date_id_seq', 4, true);
          public          postgres    false    210                       0    0    available_dates_doctor_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.available_dates_doctor_id_seq', 1, false);
          public          postgres    false    215                       0    0    customers_customer_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customers_customer_id_seq', 7, true);
          public          postgres    false    202                       0    0    doctors_doctor_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doctors_doctor_id_seq', 5, true);
          public          postgres    false    204                       0    0    vaccines_animal_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.vaccines_animal_id_seq', 1, true);
          public          postgres    false    216                       0    0    vaccines_vaccine_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.vaccines_vaccine_id_seq', 12, true);
          public          postgres    false    206            Z           2606    17307    animals animals_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (animal_id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    201            b           2606    17389    appointments appointments_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (appointment_id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    209            d           2606    17397 $   available_dates available_dates_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT available_dates_pkey PRIMARY KEY (available_date_id);
 N   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT available_dates_pkey;
       public            postgres    false    211            \           2606    17359    customers customers_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    203            ^           2606    17370    doctors doctors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (doctor_id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    205            `           2606    17381    vaccines vaccines_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (vaccine_id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public            postgres    false    207            g           2606    17444 (   appointments fk95vepu86o8syqtux9gkr71bhy    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk95vepu86o8syqtux9gkr71bhy FOREIGN KEY (animal_id) REFERENCES public.animals(animal_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk95vepu86o8syqtux9gkr71bhy;
       public          postgres    false    209    201    2906            e           2606    17439 #   animals fkb36lt3kj4mrbdx5btxmp4j60n    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fkb36lt3kj4mrbdx5btxmp4j60n;
       public          postgres    false    2908    203    201            f           2606    17459 $   vaccines fkeasdy15b2kp5j4k13x2dfudqs    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs FOREIGN KEY (animal_id) REFERENCES public.animals(animal_id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkeasdy15b2kp5j4k13x2dfudqs;
       public          postgres    false    2906    201    207            h           2606    17449 (   appointments fkmujeo4tymoo98cmf7uj3vsv76    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76 FOREIGN KEY (doctor_id) REFERENCES public.doctors(doctor_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fkmujeo4tymoo98cmf7uj3vsv76;
       public          postgres    false    205    209    2910            i           2606    17454 +   available_dates fknb419ilm71d71rm584rk460pk    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT fknb419ilm71d71rm584rk460pk FOREIGN KEY (doctor_id) REFERENCES public.doctors(doctor_id);
 U   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT fknb419ilm71d71rm584rk460pk;
       public          postgres    false    205    211    2910            �   �   x�U�;�0@g�\ ��!F@��%MM���U�Pq{R��H�mY�Yv
��Ry���F����m�*ޢE����#edIV,�<<���@F�w��-�9��kq"]���3:yt~�ѷ�Da���4�Pi.�O���Vj��.�#�ì�i0#���཈t���y���;K��|�W�"X�c_�MN�      �   9   x�3�4202�50"+0�4�4�2��B�(c�1�50Q0D�1����� M�      �   !   x�3�4202�5 "cNC.#הӄ+F��� g{}      �   ^   x�3�,I-.A#L�$�!�\bFn*��eƙY\���T��`�$�s3s���s9S�R9���L9=a*<��"�MJ-ʆl�f�1�*0�,F��� �?0      �   ?   x�3�,I-.A鹉�9z�������A.4U&H�R�K� *��b(�������� ��*
      �   G   x���,I-.Ɯ1~ d�e�4�
�r ���T�$�i�k`D EXTCU�4�r��qqq ��#~     