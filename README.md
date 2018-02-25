Tinder-Simulator

Basic   algorithm:
1. Use   a   hashmap   (You   can   use   the   build   in   HashMap   in   Java   or   use   the   one   we   built together   in   class)   with   the   bucket   as   the   key   and   User   as   the   value.   A   bucket   is   a   String that   represents   the   location   where   the   User’s   location   belongs   to.
For   example,   the   user   has   a   location   of   (45.0,   23.9).   If   I   define   the   bucket   size   to be   10,   which   mean   (0,   10)   will   be   one   bucket,   (10,   20)   will   be   one   bucket   and   so   on. Then   the   user   belongs   to   the   bucket   (40,   20).    This   can   be   improved   to   make   your solution   run   faster.
In   your   hashmap,   then   that   user   is   mapped   to   the   key   (40,   20).   When   you   have multiple   users   that   map   to   the   same   key,   you   can   simply   link   all   the   users   together   using a   linked   list   of   User.
2. Once   you   build   this   hashMap   with   all   given   user,   you   can   create   a   new   user   by   using   a Scanner.
  
3. Then   you   can   use   its   location   and   radius   to   determine   which   buckets   the   user   is touching.   If   a   user   has   a   Location   of   (40.2,   21.9)   and   its   searching   radius   is   25   miles, then   anyone   who   is   within   the   radius   should   be   considered   as   potential   match.
4. Once   you   find   all   the   potential   matches   within   the   radius,   print   out   all   the   matches.   If   you are   looking   for   any   challenges,   you   can   return   the   matches   based   on   the   distance   from the   user.
