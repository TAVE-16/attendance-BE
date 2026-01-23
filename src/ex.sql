SELECT m.username
FROM session_member sm
         JOIN member m ON sm.member_id = m.id
WHERE sm.status = 'EARLY_BIRD'
  AND sm.session_id = 20;