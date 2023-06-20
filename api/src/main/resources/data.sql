
-- TEST DATA

-- AUTHORS
INSERT INTO authors(name, birthdate, nationality)
    VALUES
        ('L. M.', '20010417', 'Spanish'),
        ('Julio Cortázar', '19140826', 'Argentinean'),
        ('Mario Benedetti', '19201014', 'Uruguayan'),
        ('Federico García Lorca', '18980605', 'Spanish'),
        ('William Shakespeare', '15640423', 'English'),
        ('Gustavo Adolfo Bécquer', '18360217', 'Spanish');

-- POEMS (TERMINAR)
INSERT INTO poems(author_id, theme, title, content)
    VALUES
        (1, 'Vida', 'Sentir', 'Soy una poeta, '             ||
                              'que sabe sentir. '           ||
                              'Pero hoy soy una escritora ' ||
                              'que no puede escribir.'),
        (1, 'Tristeza', 'No-Title', 'Estoy tan acostumbrada a escribir por sufrir, ' ||
                                    'que mi mente no es capaz de interpretar '       ||
                                    'a través de palabras '                          ||
                                    'lo que me haces sentir.'),
        (1, 'Vida', 'Vivas', 'Estemos más vivas que muertas. '       ||
                             'Estemos más sentidas que deploradas. ' ||
                             'Desdichados días con lamentos, '       ||
                             'y cálidos segundos por sonrisas '      ||
                             '¿Y si disfrutáramos el morar?'),
        (2, 'Amor', null, 'Te prometo una cosa, '  ||
                          'acordarme de vos '      ||
                          'en el último instante ' ||
                          'para que sea aún más amargo.'),
        (2, 'Amor', null, 'Ven a dormir conmigo: ' ||
                          'no haremos el amor, '   ||
                          'él nos hará.'),
        (2, 'Amor', null, 'Y si nos mordemos '  ||
                          'el dolor es dulce, ' ||
                          'y si nos ahogamos '  ||
                          'en un breve y terrible absorber simultáneo del aliento, ' ||
                          'esa instantánea muerte es bella. ' ||
                          'Y hay una sola saliva '            ||
                          'y un solo sabor a fruta madura, '  ||
                          'y yo te siento temblar contra mí ' ||
                          'como una luna en el agua'),
        (3, 'Vida', null, 'Si el corazón se aburre de querer, ' ||
                          'para qué sirve.'),
        (3, 'Amor', null, 'No me tientes, '      ||
                          'que si nos tentamos ' ||
                          'no nos podremos olvidar.'),
        (3, 'Fantasía', 'Botella al mar', 'Pongo estos seis versos en mi botella al mar ' ||
                                          'con el secreto designio de que algún día '     ||
                                          'llegue a una playa casi desierta '             ||
                                          'y un niño la encuentre y la destape '          ||
                                          'y en lugar de versos extraiga piedritas '      ||
                                          'y socorros y alertas y caracoles.'),
        (4, 'Melancolía', 'Canción de cuna', 'Ya te vemos dormida '                  ||
                                             'Tu barca es de madera por la orilla. ' ||
                                             'Blanca princesa de nunca. '            ||
                                             '¡Duerme por la noche oscura! '         ||
                                             'Cuerpo y tierra de nieve. '            ||
                                             'Duerme por el alba, ¡duerme! '         ||
                                             'Ya te alejas dormida. '                ||
                                             '¡Tu barca es bruma, sueño, por la orilla!'),
        (4, 'Vida', 'El silencio', 'Oye, hijo mío, el silencio. '  ||
                                   'Es un silencio ondulado, '     ||
                                   'un silencio, '                 ||
                                   'donde resbalan valles y ecos ' ||
                                   'y que inclina las frentes '    ||
                                   'hacia el suelo.'),
        (null, 'Love', 'Roses are Red', 'Roses are red, '    ||
                                        'Violets are blue, ' ||
                                        'Sugar is sweet, '   ||
                                        'And so are you.'),
        (5, null, 'No-Title', 'Shall I compare thee to a summer’s day? '       ||
                              'Thou art more lovely and more temperate: '      ||
                              'Rough winds do shake the darling buds of May, ' ||
                              'And summer’s lease hath all too short a date'),
        (5, null, 'Sonet CXLI', 'In faith, I do not love thee with mine eyes, '     ||
                                'For they in thee a thousand errors note; '         ||
                                'But ''tis my heart that loves what they despise, ' ||
                                'Who in despite of view is pleased to dote;'),
        (6, 'Amor', 'Podrá nublarse el sol...', 'Podrá nublarse el sol eternamente; '   ||
                                                'Podrá secarse en un instante el mar; ' ||
                                                'Podrá romperse el eje de la tierra '   ||
                                                'Como un débil cristal. '               ||
                                                '¡Todo sucederá! Podrá la muerte '      ||
                                                'Cubrirme con su fúnebre crespón; '     ||
                                                'Pero jamás en mí podrá apagarse '      ||
                                                'La llama de tu amor.'),
        (6, 'Desamor', 'X', 'Como en un libro abierto '              ||
                            'leo de tus pupilas en el fondo. '       ||
                            '¿A qué fingir el labio '                ||
                            'risas que se desmienten con los ojos? ' ||
                            '¡Llora! No te avergüences '             ||
                            'de confesar que me quisiste un poco. '  ||
                            '¡Llora! Nadie nos mira. '               ||
                            'Ya ves; yo soy un hombre... y también lloro.');